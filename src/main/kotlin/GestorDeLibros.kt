package un6.eje6_5


interface Catalogo{
    fun infoLibro(idLibro : String): Map<String,Any>
    fun existeLibro(idLibro: String): Boolean
}


class GestorDeLibros(catalogo: Catalogo){
    private val cat: Catalogo

    init {
        cat = catalogo
    }


    fun preguntarPorUnLibro() {
        val idLibro = GestorDeLibrosIUT1.obtenerID()
        GestorDeLibrosIUT1.existeLibro(cat, idLibro)
    }

    fun mostrarInfoDeUnLibro()
    {
        val idLibro = GestorDeLibrosIUT1.obtenerID()
        GestorDeLibrosIUT1.infoLibro(cat,idLibro)
    }

}


class GestorDeLibrosIUT1(){
    companion object {
        fun obtenerID(): String {
            println("Introduzca un ID: ")
            return readln()
        }
        fun existeLibro(cat: Catalogo,idLibro: String) {
            if(cat.existeLibro(idLibro))
                println("El libro $idLibro existe!")
            else
                println("El libro $idLibro NO existe!")
        }

        fun infoLibro(cat: Catalogo, idLibro: String) {
            val infoLibro = cat.infoLibro(idLibro)
            if (infoLibro.isNotEmpty())
                println("La información sobre es la siguiente\n$infoLibro")
            else
                println("No se encontró información sobre el libro")
        }

    }
}

fun main() {
    val catalogo1 = CatalogoLibrosXML("..\\DAM1-6_5-AGB\\src\\main\\kotlin\\Catalog.xml")
    val jsonLibros = """[{"id":"bk101","autor":"Pedris1","title":"Libro de eduardo 1","genre":"Ficcion 1","price":29.41,"publish_date":"Oct 1, 2000 12:00:00 AM","description":"Descripción del libro 1"}
        |,{"id":"bk102","autor":"Pedris2","title":"Libro de eduardo 2","genre":"Ficcion 2","price":29.42,"publish_date":"Oct 2, 2000 12:00:00 AM","description":"Descripción del libro 2"}
        |,{"id":"bk103","autor":"Pedris3","title":"Libro de eduardo 3","genre":"Ficcion 3","price":29.43,"publish_date":"Oct 3, 2000 12:00:00 AM","description":"Descripción del libro 3"}
        |,{"id":"bk104","autor":"Pedris4","title":"Libro de eduardo 4","genre":"Ficcion 4","price":29.44,"publish_date":"Oct 4, 2000 12:00:00 AM","description":"Descripción del libro 4"}
        |,{"id":"bk105","autor":"Pedris5","title":"Libro de eduardo 5","genre":"Ficcion 5","price":29.45,"publish_date":"Oct 5, 2000 12:00:00 AM","description":"Descripción del libro 5"}
        |]""".trimMargin()
    val catalogo2 = CatalogoLibrosJSON(jsonLibros)
    val gestorDeLibros = GestorDeLibros(catalogo2)
    gestorDeLibros.mostrarInfoDeUnLibro()
}