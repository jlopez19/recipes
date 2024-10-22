Descripción de la aplicación

La aplicación se encarga de obtener un listado de recetas por medio de peticiones API Rest, luego de esto carga el listado en una vista principal, también genera una vista detallada de una receta seleccionada y por último un mapa que ubica el origen de dicha receta.


Arquitectura utilizada
Al ser esta un aplicación móvil Android, se opta por utilizar la arquitectura MVVM siendo esta la arquitectura oficial para el desarrollo de estas aplicaciones, además, esta arquitectura se adapta perfectamente al tener una parte visual (listado, detalle, mapa), una parte de modelos de datos (consumo de API, clases para resolver la respuesta) y un intermediario entre estas 2 partes.


Librerías
Al utilizar MVVM, se implementan librerías necesarias para el funcionamiento de esta arquitectura y también para adaptarlo a los componentes de Android, estas librerías son:
- androidx.lifecycle:lifecycle-viewmodel-ktx
- androidx.activity:activity-ktx

Se implementa Livedata para que la vista solicite una información al model y espera esta respuesta para mostrar dicha información en pantalla:
- androidx.lifecycle:lifecycle-livedata-ktx

Para el consumo de APIs de una forma sencilla y correcta, se implementa el uso de Retrofit, al igual que de Gson para el parseo de la respuesta en formato JSON:
- com.squareup.retrofit2:retrofit
- com.squareup.retrofit2:converter-gson

Al momento de realizar el consumo de APIs se implementa corrutinas para realizar estos consumos de forma asíncrona:
- org.jetbrains.kotlinx:kotlinx-coroutines-android

Para tener un código más limpio, se opta por realizar inyección de dependencias por medio de Dagger Hilt:
- com.google.dagger:hilt-android
- com.google.dagger:hilt-android-compiler

Para darle un manejo más sencillo a las imágenes, se implementó Glide:
- com.github.bumptech.glide:glide

La vista del mapa implementa mapas de Google, por ello se implementa esta librería:
- com.google.android.gms:play-services-maps

Para las pruebas unitarias se implementan librerías que vienen por defecto en el proyecto, tales como JUnit 4, pero también se implementan librerías como MockK y corrutinas de testing que se adapten mejor a la arquitectura implementada:
- io.mockk:mockk
- org.jetbrains.kotlinx:kotlinx-coroutines-test
- androidx.arch.core:core-testing
