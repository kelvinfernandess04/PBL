FROM mcr.microsoft.com/mssql/server:2019-latest

# Define a senha do SA e aceita os EULA
ENV SA_PASSWORD=123456
ENV ACCEPT_EULA=Y

# Expose the SQL Server port
EXPOSE 1433

# Copia o script SQL para o contêiner
COPY script.sql /usr/src/app/

# Comando para iniciar o SQL Server
CMD /bin/bash -c "/opt/mssql/bin/sqlservr & sleep 30 && /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -i /usr/src/app/script.sql && tail -f /dev/null"
