-- MySQL Script generated by MySQL Workbench
-- Thu May 30 18:40:26 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema resstaurant
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema resstaurant
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resstaurant` DEFAULT CHARACTER SET utf8 ;
USE `resstaurant` ;

-- -----------------------------------------------------
-- Table `resstaurant`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resstaurant`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `role` INT(11) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_uindex` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resstaurant`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resstaurant`.`order` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `Note` VARCHAR(45) NULL DEFAULT NULL,
  `user_user_id` INT(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_order_user1_idx` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `resstaurant`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 53
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resstaurant`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resstaurant`.`bill` (
  `bill_id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NULL DEFAULT NULL,
  `sum` INT(11) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `user_user_id` INT(11) NOT NULL,
  `order_order_id` INT(11) NOT NULL,
  PRIMARY KEY (`bill_id`),
  INDEX `fk_bill_user1_idx` (`user_user_id` ASC) VISIBLE,
  INDEX `fk_bill_order1_idx` (`order_order_id` ASC) VISIBLE,
  CONSTRAINT `fk_bill_order1`
    FOREIGN KEY (`order_order_id`)
    REFERENCES `resstaurant`.`order` (`order_id`),
  CONSTRAINT `fk_bill_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `resstaurant`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 90
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resstaurant`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resstaurant`.`menu` (
  `menu_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameEn` VARCHAR(45) NULL DEFAULT NULL,
  `nameUa` VARCHAR(45) NULL DEFAULT NULL,
  `price` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resstaurant`.`order_has_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resstaurant`.`order_has_menu` (
  `order_order_id` INT(11) NOT NULL,
  `menu_menu_id` INT(11) NOT NULL,
  PRIMARY KEY (`order_order_id`, `menu_menu_id`),
  INDEX `fk_order_has_menu_menu1_idx` (`menu_menu_id` ASC) VISIBLE,
  INDEX `fk_order_has_menu_order_idx` (`order_order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_menu_menu1`
    FOREIGN KEY (`menu_menu_id`)
    REFERENCES `resstaurant`.`menu` (`menu_id`),
  CONSTRAINT `fk_order_has_menu_order`
    FOREIGN KEY (`order_order_id`)
    REFERENCES `resstaurant`.`order` (`order_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
