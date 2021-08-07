
object Application {

    object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            val calculator = Calculator()

            println(calculator.add("")) // 0
            println(calculator.add("1,2,5")) // 8
            println(calculator.add("1\\n,2,3")) // 6
            println(calculator.add("1,\\n2,4")) // 7
            println(calculator.add("//;\\n1;3;4")) // 8
            println(calculator.add("//\$\\n1\$2\$3")) // 6
            println(calculator.add("//@\\n2@3@8")) // 13
            println(calculator.add("2,1001")) // 2
            println(calculator.add("//***\\n1***2***3")) // 6
            println(calculator.add("//$,@\\n1$2@3")) // 6

            try {
                println(calculator.add("//@\\n-2@3@-8")) // Throw exception with [-2, -8] as not allowed numbers
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}