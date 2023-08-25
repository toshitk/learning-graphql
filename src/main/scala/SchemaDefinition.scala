import sangria.schema._

object SchemaDefinition {
  val UserType = ObjectType(
    "User",
    fields[UserRepository, User](
      Fileld("id", StringType, "User ID", resolve = _.value.id),
      // Fileld("id", StringType, "User ID", resolve = v => v.value.id),
      Field("name", StringType, "User Name", resolve = _.value.name),
      Field("hobby", StringType, "User Hobby", resolve = _.value.hobby)
    )
  )

  val idArgument = Argument("id", StringType, description = "id")

  val QueryType = ObjectType(
    "Query",
    fields[UserRepository, Unit](
      Field(
        "User",
        OptionType(UserType),
        arguments = idArgument :: Nil,
        resolve = ctx => ctx.ctx.findUserById(ctx.arg(idArgument))
      ),
      Field(
        "Users",
        ListType(UserType),
        resolve = ctx => ctx.ctx.listAll
      )
    )
  )

  val UserSchema = Schema(QueryType)
}
