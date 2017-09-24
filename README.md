# todo-app #
Es una aplicación web desarrollada como fuente de pruebas para los scripts desarrollados con Selenium.

```[shell]
cd todos-app/

npm install

node index.js
```

# Arrancar servidor Selenium #

## Selenium RC ##
`
java -jar java -jar selenium-server-standalone-2.47.1.jar
`

## Selenium Grid ##

### Instanciar HUB ###
`java -jar selenium-server-standalone-2.47.1.jar -role hub`

### Instanciar Node 1 con puerto 5555 ###
`java -jar selenium-server-standalone-2.47.1.jar -role node -hub http://localhost:4444/grid/register`

### Instanciar Node 2 con puerto 5556 ###
`java -jar selenium-server-standalone-2.47.1.jar -role node -hub http://localhost:4444/grid/register -port 5556`

### Instanciar Node 3 con puerto 5557 y solo para Firefox 3.6 en WINDOWS ###
`java -jar selenium-server-standalone-2.47.1.jar -role node -hub http://localhost:4444/grid/register -port 5557 -browser browserName=firefox,version=3.6,maxInstances=5,platform=WINDOWS`

### Instanciar Node 4 con puerto 5558 y solo para Chrome en WINDOWS ###
`java -jar selenium-server-standalone-2.47.1.jar -role node -hub http://localhost:4444/grid/register -port 5558 -browser browserName=chrome,maxInstances=3,platform=LINUX`