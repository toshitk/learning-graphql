import sangria.schema._

object SchemaDefinition {
  val UserType = ObjectType(
    "User",
    fields[UserRepository, User](
      Field("id", StringType, Some("User ID"), resolve = _.value.id),
      // Fileld("id", StringType, "User ID", resolve = v => v.value.id),
      Field("name", StringType, Some("User Name"), resolve = _.value.name),
      Field(
        "hobby",
        ListType(StringType),
        Some("User Hobby"),
        resolve = _.value.hobby
      )
    )
  )

  val CompanyType = ObjectType(
    "Company",
    fields[CompanyRepository, Company](
      Field("id", StringType, Some("Company ID"), resolve = _.value.id),
      Field("name", StringType, Some("Company Name"), resolve = _.value.name)
    )
  )

  val idArgument = Argument("id", StringType, description = "id")
  val nameArgument = Argument("name", StringType, description = "name")

  val UserQueryType = ObjectType(
    "UserQuery",
    fields[UserRepository, Unit](
      Field(
        "UserById",
        OptionType(UserType),
        arguments = idArgument :: Nil,
        resolve = ctx => ctx.ctx.findById(ctx.arg(idArgument))
      ),
      Field(
        "UserByNameExact",
        OptionType(UserType),
        arguments = nameArgument :: Nil,
        resolve = ctx => ctx.ctx.findByNameExact(ctx.arg(nameArgument))
      ),
      Field(
        "UserByNamePartial",
        OptionType(UserType),
        arguments = nameArgument :: Nil,
        resolve = ctx => ctx.ctx.findByNamePartial(ctx.arg(nameArgument))
      ),
      Field(
        "Users",
        ListType(UserType),
        resolve = ctx => ctx.ctx.listAll
      )
    )
  )

  val CompanyQueryType = ObjectType(
    "CompanyQuery",
    fields[CompanyRepository, Unit](
      Field(
        "CompanyById",
        OptionType(CompanyType),
        arguments = idArgument :: Nil,
        resolve = ctx => ctx.ctx.findById(ctx.arg(idArgument))
      )
    )
  )

  val UserSchema = Schema(UserQueryType)
  val CompanySchema = Schema(CompanyQueryType)
}
