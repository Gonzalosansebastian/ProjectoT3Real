--------------------------------------------------------
-- Archivo creado  - lunes-mayo-19-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BUTACAS
--------------------------------------------------------

  CREATE TABLE "PROGRAMACION"."BUTACAS" 
   (	"ID_BUTACA" NUMBER, 
	"FILA" VARCHAR2(10 BYTE), 
	"COLUMNA" VARCHAR2(10 BYTE), 
	"TIPO" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table ESPECTACULOS
--------------------------------------------------------

  CREATE TABLE "PROGRAMACION"."ESPECTACULOS" 
   (	"ID_ESPECTACULO" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"FECHA" DATE, 
	"PRECIO_BASE" NUMBER(6,2), 
	"PRECIO_VIP" NUMBER(6,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table RESERVAS
--------------------------------------------------------

  CREATE TABLE "PROGRAMACION"."RESERVAS" 
   (	"ID_RESERVA" NUMBER, 
	"ID_ESPECTACULO" NUMBER, 
	"ID_BUTACA" NUMBER, 
	"ESTADO" VARCHAR2(20 BYTE), 
	"ID_USUARIO" NUMBER, 
	"PRECIO" NUMBER(6,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table USUARIOS
--------------------------------------------------------

  CREATE TABLE "PROGRAMACION"."USUARIOS" 
   (	"ID_USUARIO" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE), 
	"CONTRASEŅA" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Sequence RESERVAS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PROGRAMACION"."RESERVAS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 65 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USUARIOS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PROGRAMACION"."USUARIOS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
REM INSERTING into PROGRAMACION.BUTACAS
SET DEFINE OFF;
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('1','A','1','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('2','A','2','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('3','A','3','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('4','A','4','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('5','A','5','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('6','A','6','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('7','B','1','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('8','B','2','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('9','B','3','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('10','B','4','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('11','B','5','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('12','B','6','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('13','C','1','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('14','C','2','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('15','C','3','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('16','C','4','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('17','C','5','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('18','C','6','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('19','D','1','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('20','D','2','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('21','D','3','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('22','D','4','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('23','D','5','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('24','D','6','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('25','E','1','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('26','E','2','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('27','E','3','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('28','E','4','VIP');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('29','E','5','Normal');
Insert into PROGRAMACION.BUTACAS (ID_BUTACA,FILA,COLUMNA,TIPO) values ('30','E','6','Normal');
REM INSERTING into PROGRAMACION.ESPECTACULOS
SET DEFINE OFF;
Insert into PROGRAMACION.ESPECTACULOS (ID_ESPECTACULO,NOMBRE,FECHA,PRECIO_BASE,PRECIO_VIP) values ('1','Halloween',to_date('25/04/25','DD/MM/RR'),'10','20');
Insert into PROGRAMACION.ESPECTACULOS (ID_ESPECTACULO,NOMBRE,FECHA,PRECIO_BASE,PRECIO_VIP) values ('4','Boliviana',to_date('05/06/25','DD/MM/RR'),'1','3');
Insert into PROGRAMACION.ESPECTACULOS (ID_ESPECTACULO,NOMBRE,FECHA,PRECIO_BASE,PRECIO_VIP) values ('2','Paddington en Peru',to_date('28/09/26','DD/MM/RR'),'500','1000');
Insert into PROGRAMACION.ESPECTACULOS (ID_ESPECTACULO,NOMBRE,FECHA,PRECIO_BASE,PRECIO_VIP) values ('3','Sonic 2 movie',to_date('01/02/01','DD/MM/RR'),'5','10');
REM INSERTING into PROGRAMACION.RESERVAS
SET DEFINE OFF;
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('57','4','22','ocupada','4','1');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('23','2','27','ocupada','21','500');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('24','2','17','ocupada','21','1000');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('25','2','28','ocupada','21','1000');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('59','4','13','ocupada','4','1');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('60','4','23','ocupada','4','1');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('58','4','12','ocupada','4','3');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('61','1','26','ocupada','4','20');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('62','1','25','ocupada','4','10');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('63','1','24','ocupada','4','20');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('32','1','27','ocupada','21','10');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('64','1','23','ocupada','4','10');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('37','3','1','ocupada','4','5');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('38','3','2','ocupada','4','10');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('39','3','12','ocupada','4','10');
Insert into PROGRAMACION.RESERVAS (ID_RESERVA,ID_ESPECTACULO,ID_BUTACA,ESTADO,ID_USUARIO,PRECIO) values ('40','3','3','ocupada','4','5');
REM INSERTING into PROGRAMACION.USUARIOS
SET DEFINE OFF;
Insert into PROGRAMACION.USUARIOS (ID_USUARIO,NOMBRE,EMAIL,"CONTRASEŅA") values ('41','loquete','caca','1234');
Insert into PROGRAMACION.USUARIOS (ID_USUARIO,NOMBRE,EMAIL,"CONTRASEŅA") values ('4','Gonzalo','caca@gmail.com','1234');
Insert into PROGRAMACION.USUARIOS (ID_USUARIO,NOMBRE,EMAIL,"CONTRASEŅA") values ('21','Pepe','pepe@gmail.com','1234');
--------------------------------------------------------
--  Constraints for Table ESPECTACULOS
--------------------------------------------------------

  ALTER TABLE "PROGRAMACION"."ESPECTACULOS" ADD PRIMARY KEY ("ID_ESPECTACULO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USUARIOS
--------------------------------------------------------

  ALTER TABLE "PROGRAMACION"."USUARIOS" ADD PRIMARY KEY ("ID_USUARIO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BUTACAS
--------------------------------------------------------

  ALTER TABLE "PROGRAMACION"."BUTACAS" ADD PRIMARY KEY ("ID_BUTACA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table RESERVAS
--------------------------------------------------------

  ALTER TABLE "PROGRAMACION"."RESERVAS" ADD PRIMARY KEY ("ID_RESERVA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table RESERVAS
--------------------------------------------------------

  ALTER TABLE "PROGRAMACION"."RESERVAS" ADD FOREIGN KEY ("ID_ESPECTACULO")
	  REFERENCES "PROGRAMACION"."ESPECTACULOS" ("ID_ESPECTACULO") ENABLE;
  ALTER TABLE "PROGRAMACION"."RESERVAS" ADD FOREIGN KEY ("ID_BUTACA")
	  REFERENCES "PROGRAMACION"."BUTACAS" ("ID_BUTACA") ENABLE;
  ALTER TABLE "PROGRAMACION"."RESERVAS" ADD FOREIGN KEY ("ID_USUARIO")
	  REFERENCES "PROGRAMACION"."USUARIOS" ("ID_USUARIO") ENABLE;
