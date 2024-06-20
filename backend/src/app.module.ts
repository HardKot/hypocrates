import { Module } from '@nestjs/common';
import {GraphQLModule} from "@nestjs/graphql";
import {ApolloDriver, ApolloDriverConfig} from "@nestjs/apollo";
import {PrismaService} from "./infrastructure/service/prisma.service";
import {StaffRepository} from "./infrastructure/repository/staff.repository";
import {EventEmitterModule} from "@nestjs/event-emitter";

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
    }),
    EventEmitterModule.forRoot(),
  ],
  controllers: [],
  providers: [PrismaService, StaffRepository],
})
export class AppModule {}
