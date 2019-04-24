package edu.eci.cvds.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.io.Serializable;


@SuppressWarnings("deprecation")
@ManagedBean(name="shiroBean")
@SessionScoped
public class ShiroSecurityBean implements Serializable {

	private String userName;
	private String userPassword;
	private boolean rememberMe;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rme) {
		rememberMe = rme;
	}

	public void loginUser() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, new Sha256Hash(userPassword).toHex(), rememberMe);
			currentUser.getSession().setAttribute("Correo",userName);

			currentUser.login(token);

			FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
		} catch (UnknownAccountException e) {
			FacesContext.getCurrentInstance().addMessage("shiro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no registrado", "Este usuario no se encuentra en nuestra base de datos"));
		} catch (IncorrectCredentialsException e) {
			FacesContext.getCurrentInstance().addMessage("shiro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta", "La contraseña ingresada no es correcta"));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("shiro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo en servidor", "Error"));
		}
	}

	public void isLogged(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.getSession().getAttribute("Correo") != null){
			try{
				FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
			}catch (IOException e){
				FacesContext.getCurrentInstance().addMessage("shiro", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al redireccionar","Ocurrio un error en el servidor"));
			}
		}
	}

}
