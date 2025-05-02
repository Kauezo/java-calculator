@echo off
setlocal

REM Verificar se o JavaFX está instalado
if not exist "C:\Users\Kaue\Downloads\javafx-sdk-21.0.7\lib" (
    echo JavaFX SDK não encontrado!
    echo Por favor, baixe o JavaFX SDK 21.0.7 de https://gluonhq.com/products/javafx/
    echo E extraia para C:\Users\Kaue\Downloads\javafx-sdk-21.0.7
    pause
    exit /b 1
)

REM Definir variáveis
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "PATH=%JAVA_HOME%\bin;%PATH%"
set "JAVAFX_HOME=C:\Users\Kaue\Downloads\javafx-sdk-21.0.7"

REM Verificar se o Java está instalado
where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo Java não encontrado!
    echo Por favor, instale o Java 21 ou superior
    pause
    exit /b 1
)

REM Criar diretório de classes se não existir
if not exist "target\classes" mkdir "target\classes"

REM Compilar
echo Compilando...
javac --module-path "%JAVAFX_HOME%\lib" --add-modules javafx.controls,javafx.fxml -d target/classes src/main/java/com/example/calculadora/*.java
if %ERRORLEVEL% neq 0 (
    echo Erro na compilação!
    pause
    exit /b 1
)

REM Copiar recursos
echo Copiando recursos...
xcopy /Y /I src\main\resources\* target\classes\ >nul

REM Executar
echo Executando...
java --module-path "%JAVAFX_HOME%\lib" --add-modules javafx.controls,javafx.fxml -cp target/classes com.example.calculadora.Main

pause 
