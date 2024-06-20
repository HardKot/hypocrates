export abstract class User {
  constructor(
    public id?: string,
    public firstname?: string,
    public lastname?: string,
    public patronymic?: string,
    public birthday?: Date,
    public avatarUrl?: string,
    public phone?: string,
    public email?: string,
  ) {
  }
}
