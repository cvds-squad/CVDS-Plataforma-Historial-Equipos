create table Usuarios(
nombre varchar(30) notnull,
correo varchar(30) primary key,
userrol int(5) references rolesUsuario(idRol),
contraseña varchar (30) notnull,
estado varchar(30) notnull);

create table Laboratorios(
idLab int(5) primary key,
nombre varchar(30) notnull,
usuario varchar(30) references Usuarios(correo),
descripcion varchar(500));

create table Equipos(
idEquipo int(5) primary key,
labs int (5) references Laboratorios(idLab),
Novedades varchar(500);

create table Elementos(
idElemento int(5) primary key,
eqps int(5) references Equipos(idEquipo),
tipelement int(5) references TipoElemento(idTElemento),
marca varchar(20),
Descripcion varchar(500));

create table TipoElemento(
idTElemento int(5) primary key,
Tipo varchar(100),
Descripción varchar(100));

create table rolesUsuario(
idRol int(5) primary key,
descripcion varchar(500),
nombre varchar(30));
)