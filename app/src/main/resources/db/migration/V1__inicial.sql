CREATE TABLE `contabilidade` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cnpj` VARCHAR(50) NOT NULL,
  `dominio` VARCHAR(30) NOT NULL,
  `logo` VARCHAR(100) NULL DEFAULT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `tipo_documento` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `contabilidade_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tipo_doc_contabilidade`
    FOREIGN KEY (`contabilidade_id`)
    REFERENCES `contabilidade` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_tipo_doc_contabilidade` ON `tipo_documento` (`contabilidade_id` ASC);



CREATE TABLE `config_metadado` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_competencia` BIT(1) NOT NULL,
  `desabilitado` BIT(1) NOT NULL,
  `descricao` VARCHAR(70) NOT NULL,
  `nome` VARCHAR(70) NOT NULL,
  `obrigatorio` BIT(1) NOT NULL,
  `tipo_documento_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_config_metadado_tp_doc`
    FOREIGN KEY (`tipo_documento_id`)
    REFERENCES `tipo_documento` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_config_metadado_tp_doc` ON `config_metadado` (`tipo_documento_id` ASC);


CREATE TABLE `config_pasta` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `contato` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(120) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `empresa` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(100) NOT NULL,
  `cnpj` VARCHAR(50) NOT NULL,
  `estado` VARCHAR(50) NOT NULL,
  `logradouro` VARCHAR(100) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `contabilidade_id` BIGINT(20) NOT NULL,
  `contato_a_id` BIGINT(20) NOT NULL,
  `contato_b_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_empresa_contato_1`
    FOREIGN KEY (`contato_a_id`)
    REFERENCES `contato` (`id`),
  CONSTRAINT `fk_empresa_contabilidade`
    FOREIGN KEY (`contabilidade_id`)
    REFERENCES `contabilidade` (`id`),
  CONSTRAINT `fk_empresa_contato_2`
    FOREIGN KEY (`contato_b_id`)
    REFERENCES `contato` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_empresa_contabilidade` ON `empresa` (`contabilidade_id` ASC);

CREATE INDEX `fk_empresa_contato_1` ON `empresa` (`contato_a_id` ASC);

CREATE INDEX `fk_empresa_contato_2` ON `empresa` (`contato_b_id` ASC);



CREATE TABLE `usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `perfil` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NULL DEFAULT NULL,
  `contabilidade_id` BIGINT(20) NOT NULL,
  `empresa_id` BIGINT(20) NULL DEFAULT NULL,
  `admin` BIT(1) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `ultimo_acesso` DATETIME NULL DEFAULT NULL,
  `hash_ativacao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuario_empresa`
    FOREIGN KEY (`empresa_id`)
    REFERENCES `empresa` (`id`),
  CONSTRAINT `fk_usuario_contabilidade`
    FOREIGN KEY (`contabilidade_id`)
    REFERENCES `contabilidade` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_usuario_contabilidade` ON `usuario` (`contabilidade_id` ASC);

CREATE INDEX `fk_usuario_empresa` ON `usuario` (`empresa_id` ASC);

CREATE TABLE `documento` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_checkin` DATETIME NULL DEFAULT NULL,
  `data_competencia` DATETIME NOT NULL,
  `data_envio` DATETIME NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `tipo_documento_id` BIGINT(20) NOT NULL,
  `usuario_id` BIGINT(20) NOT NULL,
  `usuario_checkin_id` BIGINT(20) NULL DEFAULT NULL,
  `tamanho` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_documento_usuario_add`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_documento_usuario_checkin`
    FOREIGN KEY (`usuario_checkin_id`)
    REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_documento_tp_doc`
    FOREIGN KEY (`tipo_documento_id`)
    REFERENCES `tipo_documento` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_documento_tp_doc` ON `documento` (`tipo_documento_id` ASC);

CREATE INDEX `fk_documento_usuario_add` ON `documento` (`usuario_id` ASC);

CREATE INDEX `fk_documento_usuario_checkin` ON `documento` (`usuario_checkin_id` ASC);


CREATE TABLE `pasta` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `empresa_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pasta_empresa`
    FOREIGN KEY (`empresa_id`)
    REFERENCES `empresa` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_pasta_empresa` ON `pasta` (`empresa_id` ASC);


CREATE TABLE `permissao_documento` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `documento_id` BIGINT(20) NOT NULL,
  `pasta_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_permissao_doc`
    FOREIGN KEY (`documento_id`)
    REFERENCES `documento` (`id`),
  CONSTRAINT `fk_permissao_pasta`
    FOREIGN KEY (`pasta_id`)
    REFERENCES `pasta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_permissao_doc` ON `permissao_documento` (`documento_id` ASC);

CREATE INDEX `fk_permissao_pasta` ON `permissao_documento` (`pasta_id` ASC);


CREATE TABLE `permissao_usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pasta_id` BIGINT(20) NOT NULL,
  `usuario_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_permissao_usu_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_permissao_usu_pasta`
    FOREIGN KEY (`pasta_id`)
    REFERENCES `pasta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_permissao_usu_pasta` ON `permissao_usuario` (`pasta_id` ASC);

CREATE INDEX `fk_permissao_usu_usuario` ON `permissao_usuario` (`usuario_id` ASC);


CREATE TABLE `valor_metadado` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `valor` VARCHAR(255) NOT NULL,
  `config_metadado_id` BIGINT(20) NOT NULL,
  `documento_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_valor_config`
    FOREIGN KEY (`config_metadado_id`)
    REFERENCES `config_metadado` (`id`),
  CONSTRAINT `fk_valor_documento`
    FOREIGN KEY (`documento_id`)
    REFERENCES `documento` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_valor_config` ON `valor_metadado` (`config_metadado_id` ASC);
CREATE INDEX `fk_valor_documento` ON `valor_metadado` (`documento_id` ASC);

