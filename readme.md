# Examen ED

**_En este archivo _readme.md_ vamos a documentar los
cambios realizados en la refactorizacion de codigo._**

## Error blocked

Se ha eliminado el label presente en el codigo,
el cual daba un problema tipo **blocker**

## Error high

He cambiado 4 mensajes 'Nota para' por un _String mensaje_
que dice 'Nota para'

## Cambio a loggger

He cambiado todo los syso por loggers

>logger.log(Level.INFO,"Nota final de Entornos de Desarrollo: {0}",notaFinal);

## Condicion redundante

He quitado una condicion redundante
else if(notasRa!=null)

## He refactorizado la complejidad cognitiva de 70 a 15

He cambiado el metodo que tenia por otro que realiza la misma funcion de forma mas simple y estructurada.