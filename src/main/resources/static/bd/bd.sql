SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `receta` ;
CREATE SCHEMA IF NOT EXISTS `receta` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `receta` ;

-- -----------------------------------------------------
-- Table `receta`.`rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`rol` ;

CREATE TABLE IF NOT EXISTS `receta`.`rol` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`usuario` ;

CREATE TABLE IF NOT EXISTS `receta`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` LONGTEXT NULL,
  `fechaRegistro` DATETIME NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `nombres` VARCHAR(200) NULL,
  `rol_id` INT NOT NULL,
  `apellidos` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_rol1_idx` (`rol_id` ASC),
  CONSTRAINT `fk_usuario_rol1`
    FOREIGN KEY (`rol_id`)
    REFERENCES `receta`.`rol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`receta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`receta` ;

CREATE TABLE IF NOT EXISTS `receta`.`receta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `video` LONGTEXT NULL,
  `alcance` INT NULL,
  `preparacion` LONGTEXT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `usuario_id` INT NOT NULL,
  `fechaRegistro` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_receta_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_receta_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `receta`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`resenia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`resenia` ;

CREATE TABLE IF NOT EXISTS `receta`.`resenia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `receta_id` INT NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `comentario` VARCHAR(45) NULL,
  `estrella` INT NOT NULL DEFAULT 5,
  `fechaRegistro` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Resenia_usuario1_idx` (`usuario_id` ASC),
  INDEX `fk_Resenia_receta1_idx` (`receta_id` ASC),
  CONSTRAINT `fk_Resenia_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `receta`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resenia_receta1`
    FOREIGN KEY (`receta_id`)
    REFERENCES `receta`.`receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`megusta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`megusta` ;

CREATE TABLE IF NOT EXISTS `receta`.`megusta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `usuario_id` INT NOT NULL,
  `receta_id` INT NOT NULL,
  `fechaRegistro` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_megusta_usuario1_idx` (`usuario_id` ASC),
  INDEX `fk_megusta_receta1_idx` (`receta_id` ASC),
  CONSTRAINT `fk_megusta_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `receta`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_megusta_receta1`
    FOREIGN KEY (`receta_id`)
    REFERENCES `receta`.`receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`categoria` ;

CREATE TABLE IF NOT EXISTS `receta`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` LONGTEXT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`tipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`tipo` ;

CREATE TABLE IF NOT EXISTS `receta`.`tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `receta_id` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_tipo_receta1_idx` (`receta_id` ASC),
  INDEX `fk_tipo_categoria1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_tipo_receta1`
    FOREIGN KEY (`receta_id`)
    REFERENCES `receta`.`receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `receta`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `receta`.`foto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `receta`.`foto` ;

CREATE TABLE IF NOT EXISTS `receta`.`foto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(45) NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `receta_id` INT NOT NULL,
  `fechaRegistro` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_foto_receta1_idx` (`receta_id` ASC),
  CONSTRAINT `fk_foto_receta1`
    FOREIGN KEY (`receta_id`)
    REFERENCES `receta`.`receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
