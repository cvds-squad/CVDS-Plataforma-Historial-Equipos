package edu.eci.cvds.beans;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;


@SuppressWarnings("deprecation")
@ManagedBean(name="shiroBean")
@RequestScoped
public class ShiroSecurityBean extends BasePageBean {
	
	private String userName;
	private String userPassword;
	
	public ShiroSecurityBean() {}

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
	
	public void loginUser() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, new Sha256Hash(userPassword).toHex());

            
            currentUser.login(token);
		}catch(UnknownAccountException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo en autenticacion", "Usuario no registrado"));
		}catch (IncorrectCredentialsException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo en autenticacion", "Contraseña incorrecta"));
		}
	}
	
	public void logOutUser() {
		SecurityUtils.getSubject().logout();
	}
}
