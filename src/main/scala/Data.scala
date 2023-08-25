case class User(
    id: String,
    name: String,
    hobby: List[String]
)

class UserRepository {
  def findById(id: String): Option[User] = {
    UserRepository.users.find(_.id == id)
  }
  def listAll(): List[User] = {
    UserRepository.users
  }
}

object UserRepository {
  val users = List(
    User("1", "Alice", List("cooking", "camping")),
    User("2", "Bob", List("fishing"))
  )
}
