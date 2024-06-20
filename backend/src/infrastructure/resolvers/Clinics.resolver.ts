import {Args, Resolver, Query, ResolveField} from "@nestjs/graphql";
import {ClinicModel} from "@/infrastructure/models/Clinic.model";
import {ClinicRepository} from "@/infrastructure/repository/clinic.repository";

@Resolver(of => ClinicModel)
export class ClinicsResolver {
  constructor(
    private clinicRepository: ClinicRepository
  ) {
  }

  @Query(returns => ClinicModel)
  async clinic(@Args('id') id: string) {
    return this.clinicRepository.getById(id);
  }
}
