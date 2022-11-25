CREATE SCHEMA formacaojava;

CREATE TABLE `formacaojava`.`cliente` (
  `clienteId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  UNIQUE INDEX `clienteId_UNIQUE` (`clienteId` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  PRIMARY KEY (`cpf`));

CREATE TABLE `formacaojava`.`curso` (
  `cursoId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(500) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  UNIQUE INDEX `clienteId_UNIQUE` (`cursoId` ASC) VISIBLE,
  PRIMARY KEY (`cursoId`));

CREATE TABLE `formacaojava`.`pagamento` (
  `cursoId` INT UNSIGNED NOT NULL,
  `clienteId` INT UNSIGNED NOT NULL,
  `data` DATE NOT NULL,
  `pagamentoId` INT NOT NULL AUTO_INCREMENT,
  `pagamentocol` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `pagamentoId_UNIQUE` (`pagamentoId` ASC) VISIBLE,
  INDEX `clienteId_idx` (`clienteId` ASC) VISIBLE,
  PRIMARY KEY (`cursoId`, `clienteId`),
  CONSTRAINT `cursoId`
    FOREIGN KEY (`cursoId`)
    REFERENCES `formacaojava`.`curso` (`cursoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `clienteId`
    FOREIGN KEY (`clienteId`)
    REFERENCES `formacaojava`.`cliente` (`clienteId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE USER formacaojava@localhost IDENTIFIED WITH mysql_native_password BY 'formacaojava';
GRANT ALL ON formacaojava.* to formacaojava@localhost;