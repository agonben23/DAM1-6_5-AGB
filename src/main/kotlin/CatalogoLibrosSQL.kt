package un6.eje6_5

import java.sql.Connection

class CatalogoLibrosSQL(private val connection: Connection) :Catalogo{


    override fun infoLibro(idLibro: String): Map<String, Any> {
        val query = connection.prepareStatement("SELECT * FROM LIBROS")
        val result = query.executeQuery()
        val map = mutableMapOf<String,Any>()
        while(result.next()){
            val id = result.getString("id")
            if (id == idLibro){
                map.putIfAbsent("ID",id)
                map.putIfAbsent("Author",result.getString("Author"))
                map.putIfAbsent("Title",result.getString("Title"))
                map.putIfAbsent("Genre",result.getString("Genre"))
                map.putIfAbsent("Price",result.getDouble("Price"))
                map.putIfAbsent("Price",result.getDouble("Price"))
                map.putIfAbsent("Publish date",result.getDate("publish_date"))
                map.putIfAbsent("Description",result.getString("Description"))

            }
        }
        return map
    }

    override fun existeLibro(idLibro: String): Boolean {
        val query = connection.prepareStatement("SELECT ID FROM LIBROS")
        val result = query.executeQuery()
        var encontrado = false
        while(result.next()){
            val id = result.getString("id")
            if (id == idLibro){
                encontrado = true
            }
        }
        return encontrado
    }

    override fun borrarLibro(idLibro: String): Boolean {
        val query = connection.prepareStatement("SELECT ID FROM LIBROS")
        val result = query.executeQuery()
        var borrado = false
        while(result.next()){
            val id = result.getString("id")
            if (id == idLibro){
                val eliminar = connection.prepareStatement("DELETE FROM LIBROS WHERE ID = '$idLibro' ")
                eliminar.executeQuery()
                borrado = true
            }
        }
        return borrado
    }


}