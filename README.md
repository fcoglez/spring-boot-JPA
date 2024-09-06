# COSAS A SABER SOBRE JPA

- @Transactional() -> significa que el método o clase utilizará el comportamiento predeterminado de lectura-escritura.

- @Transactional(readOnly = true) -> Esta anotación se utiliza en Spring para marcar un método o una clase entera como transacción de solo lectura.

- @GeneratedValue(strategy = GenerationType.IDENTITY) -> Indica que el campo id se auto-incrementará en la base de datos.

- Cuando tenemos una clase @Entity, si tenemos un constructor con parametros, estamos obligados siempre a poner un constructor vacio.

- Cuando trabajamos con JPA, es recomendable trabajar con Optional<T>. Se usa  para evitar problemas con null y mejorar la seguridad y claridad del código en Java.









  