
## Examen Zara Project

Proyecto por el cual se calculará cual será el capital final obtenido, si una persona que lleva comprando acciones desde la apertura de IBEX, desea vender las mismas el día 28-dic-2017 al valor del cierre de la cotización.


## Sobre el proyecto

El proyecto consta de 3 clases:
- Main (mainZara). Desde donde ejecuto cada método.
- Métodos. Aquí encuentro la lógica.
- Datos. Se guardarán los datos leídos.

#### Main:
Desde aquí iniciaré la ejecución de mi programa.

#### Metodos:
Aquí encontraremos:
- leerValores: Aquí realizo la lectura de mi csv mediante "BufferedReader". Mediante un "split" separo mis datos de ingreso por sus ";" , y genero un array String donde se guardarán separados mi fecha de acción, día apertura, y día cierre. Luego paso los valores de éste "String [] fields" a mi clase datos, generando un "ArrayList<Datos> valores". Las fechas como "LocalDate", y las acciones de compra y venta como "Double".
- diaViernes: Obtengo el último día Viernes de cada mes, donde se realizará la compra de acción. Para obtenerlo, primero obtuve el último día hábil de la semana. Luego calculé el residuo respecto a los 7 días de la semana (así, obtuve los días que faltarían para terminar semana). Luego resté ese residuo a la última fecha del mes. Generé un "ArrayList<LocalDate> diaVier".
- diaCompra: Obtengo el día que compró su acción. Dos bucles for. El de día Viernes de compra por fuera, dentro el de las fechas con aperturas y cierres de acciones. Comienza el for de días de acciones, si encuentra uno igual al día de compra, guarda el día en "ArrayList<Datos> compraAcc". Si el día de compra no existe, llegará a un if que le asignará como día comprado el día posterior, q fué guardado de antemano en una variable auxiliar "Datos olderCloser". Fuera de mi bucle for, utilizo un while para recorrer solamente 20 días.
- cantAcc: Obtengo la cantidad total de acciones que compró, mediante "Math.round" para tener solamente 3 decimales, y sumándolas una a una.
- ventAcc: Venta de las acciones al precio de cierre del último día de bolsa.

#### Datos:

La clase contendrá los valores de fechas y acciones, y tendrá un "constructor", métodos "getter y setters", y "toString" para poder visualizar los valores realizando los llamados.