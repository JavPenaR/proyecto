# proyecto

Este es el proyecto de ejemplo para demostrar un microservicio con HATEOAS y Docker.

## HATEOAS

Para Hateoas, tiene que guiarse de los archivos UserModel, UserModelAssembler y UserController.

UserModel es el model utilizado para representar los datos para la cara de HATEOAS, para funcionar correctamente, debe crear estea clase y extenderla de RepresentationModel<>, donde dentro de los <> debe incluir el mismo nombre de su clase, generando esa dependencia redundante.

UserModelAssembler es la clase que utiliza los datos representados en el model, ademas de agregar los links a los diferentes vinculos de los metodos del controller. Esta clase debe implementar RepresentationModelAssembler<>, donde el primer elemento es la clase original y el segundo elemento el modelo. Al implementar una interfaz, debe sobreescribir el metodo toModel() y crear lo que se entregara en la respuesta.

Por ultimo, debe adaptar todo el controlador para que entrege el modelo creado anteriormente.


## Docker

Para incluir docker en su proyecto, pueden copiar y pegar en su proyecto los 3 archivos de docker (dockerfile, .dockerignore y docker-compose). Los cambios que se deben hacer es:
identificar el nombre que se genera su ejecutable (.jar) del proyecto entero.
configurar el application.properties para que sea generico (reemplazar localhost por mysql)
cambiar la configuracion de puertos en dockerfile/application.properties
