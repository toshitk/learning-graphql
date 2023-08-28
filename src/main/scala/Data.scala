case class User(
    id: String,
    name: String,
    hobby: List[String]
)
case class Company(
    id: String,
    name: String
)

class UserRepository {
  def findById(id: String): Option[User] = {
    UserRepository.users.find(_.id == id)
  }
  def findByNameExact(name: String): Option[User] = {
    UserRepository.users.find(_.name == name)
  }
  def findByNamePartial(pattern: String): Option[User] = {
    UserRepository.users.find(_.name.contains(pattern))
  }
  def listAll(): List[User] = {
    UserRepository.users
  }
}

class CompanyRepository {
  def findById(id: String): Option[Company] = {
    CompanyRepository.companies.find(_.id == id)
  }
}

object UserRepository {
  val users = List(
    User("1", "Alice", List("cooking", "camping")),
    User("2", "Bob", List("fishing"))
  )
}

object CompanyRepository {
  val companies = List(
    Company("1", "Apple"),
    Company("2", "Alphabet")
  )
}
