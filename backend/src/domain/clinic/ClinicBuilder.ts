import {Clinic} from "./Clinic";

export class ClinicBuilder {
  private id?: string
  private name?: string
  private address?: string
  private avatarUrl?: string

  setId(id: string) {
    this.id = id;
    return this;
  }

  setName(name: string) {
    this.name = name;
    return this;
  }

  setAddress(address: string) {
    this.address = address;
    return this;
  }

  setAvatarUrl(avatarUrl: string) {
    this.avatarUrl = avatarUrl;
    return this;
  }

  build() {
    return new Clinic(this.id, this.name, this.address, this.avatarUrl)
  }
}
