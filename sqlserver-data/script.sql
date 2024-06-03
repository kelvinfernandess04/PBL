-- Criação do banco de dados
CREATE DATABASE PBL;
GO

-- Uso do banco de dados
USE PBL;
GO

-- Criação da tabela de usuários
CREATE TABLE Usuarios (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Nome NVARCHAR(100) NOT NULL,
    IdEmpresa INT NOT NULL,
    Cargo NVARCHAR(50) NOT NULL,
    Senha NVARCHAR(50) NOT NULL
);
GO

-- Inserindo alguns dados iniciais
INSERT INTO Usuarios (Nome, IdEmpresa, Cargo, Senha) VALUES 
('Kelvin Fernandes Silva', 1, 'Developer', 'senha123'),
('William Galvonas Apuzzo Miyaura', 1, 'Developer', 'senha123'),
('Evandro Ijanques Junior', 1, 'Developer', 'senha123'),
('Heytor Kyoshi Sato', 1, 'Developer', 'senha123');
GO

-- Stored procedure para listagem de usuários
CREATE PROCEDURE spListagemUsuarios
AS
BEGIN
    SELECT Id, Nome, IdEmpresa, Cargo, Senha FROM Usuarios ORDER BY Id;
END;
GO

-- Stored procedure para inserção de usuário
CREATE PROCEDURE spInsert_Usuarios
    @nome NVARCHAR(100),
    @idEmpresa INT,
    @cargo NVARCHAR(50),
    @senha NVARCHAR(50)
AS
BEGIN
    INSERT INTO Usuarios (Nome, IdEmpresa, Cargo, Senha)
    VALUES (@nome, @idEmpresa, @cargo, @senha);
END;
GO

-- Stored procedure para atualização de usuário
CREATE PROCEDURE spUpdate_Usuarios
    @id INT,
    @nome NVARCHAR(100),
    @idEmpresa INT,
    @cargo NVARCHAR(50),
    @senha NVARCHAR(50)
AS
BEGIN
    UPDATE Usuarios
    SET Nome = @nome, IdEmpresa = @idEmpresa, Cargo = @cargo, Senha = @senha
    WHERE Id = @id;
END;
GO

-- Stored procedure para deleção de usuário
CREATE PROCEDURE spDelete
    @id INT,
    @tabela NVARCHAR(50)
AS
BEGIN
    DECLARE @sql NVARCHAR(MAX)
    SET @sql = N'DELETE FROM ' + @tabela + ' WHERE Id = @id'
    EXEC sp_executesql @sql, N'@id INT', @id;
END;
GO

-- Stored procedure para consultar usuário por ID
CREATE PROCEDURE spConsulta
    @id INT,
    @tabela NVARCHAR(50)
AS
BEGIN
    DECLARE @sql NVARCHAR(MAX)
    SET @sql = N'SELECT * FROM ' + @tabela + ' WHERE Id = @id'
    EXEC sp_executesql @sql, N'@id INT', @id;
END;
GO

-- Stored procedure para obter o próximo ID
CREATE PROCEDURE spProximoId
    @tabela NVARCHAR(50)
AS
BEGIN
    DECLARE @sql NVARCHAR(MAX)
    SET @sql = N'SELECT ISNULL(MAX(Id), 0) + 1 AS ProximoId FROM ' + @tabela
    EXEC sp_executesql @sql;
END;
GO

-- Stored procedure para validar usuário
CREATE PROCEDURE spValidaUsuario
    @nome NVARCHAR(100),
    @senha NVARCHAR(50)
AS
BEGIN
    SELECT Id, Nome, IdEmpresa, Cargo, Senha
    FROM Usuarios
    WHERE Nome = @nome AND Senha = @senha;
END;
GO

