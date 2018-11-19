# Emprestimo
Trabalho 04


Script de criação da tabela 

CREATE TABLE `emprestimo`.`emprestimo` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(60) NOT NULL,
  `CPF` VARCHAR(11) NOT NULL,
  `SALARIO` DECIMAL(10,2) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `DT_NASCIMENTO` DATE NOT NULL,
  `VALOR` DECIMAL(10,2) NOT NULL,
  `TELEFONE` VARCHAR(11) NOT NULL,
  `SEXO` CHAR(1) NOT NULL,
  PRIMARY KEY (`ID`));

  
  
  link de apoio
  
  https://medium.com/@antonio.gabriel/javafx-trabalhando-com-tableview-5cc1065babab