# COSAS A SABER SOBRE JPA

- Cuando tenemos una clase @Entity, si tenemos un constructor con parametros, estamos obligados siempre a poner un constructor vacio.

- @GeneratedValue(strategy = GenerationType.IDENTITY) -> Indica que el campo id se auto-incrementará en la base de datos.
  