create table rolesUsuario(
idRol serial primary key,
descripcion varchar(500),
nombre varchar(30));


create table TipoElemento(
Tipo varchar(100) primary key,
Descripción varchar(100));


create table Usuarios(
nombre varchar(30) not null,
correo varchar(100) primary key,
userrol int references rolesUsuario(idRol),
contraseña varchar (100) not null,
estado varchar(30) not null);

create table Laboratorios(
idLab serial primary key,
nombre varchar(30) not null,
usuario varchar(100) references Usuarios(correo),
descripcion varchar(500),
de_baja bool not null,
fecha_creacion Date,
fecha_cierre Date);

create table Equipos(
idEquipo serial primary key,
labs int references Laboratorios(idLab),
Novedades varchar(500),
disponible bool not null,
de_baja bool);

create table Elementos(
idElemento serial primary key,
eqps int references Equipos(idEquipo),
tipelement varchar references TipoElemento(Tipo),
marca varchar(20),
Descripcion varchar(500),
disponible bool,
de_baja bool);

create table Novedad(
idNovedad serial primary key,
equipoAsociado int references Equipos(idEquipo),
elementoAsociado int references elementos(idElemento),
fecha date not null,
titulo varchar(50),
responsable varchar(100) references usuarios(correo),
detalle varchar(500));

insert into tipoelemento values ('TECLADO','Teclado de pc');
insert into tipoelemento values ('TORRE','Torre de pc');
insert into tipoelemento values ('PANTALLA','pantalla de pc');
insert into tipoelemento values ('MOUSE','Mouse de pc');

insert into rolesUsuario (descripcion,nombre) values ('test admin','test');
insert into Usuarios (nombre, correo, userrol, contraseña, estado) values ('testname','predeterminado',1,'123','activo');
