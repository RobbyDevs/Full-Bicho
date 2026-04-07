import { UserRequestDTO } from './UserRequestDTO';

export type RoleType = 'REGULAR' | 'ADMIN';

export interface AdminRequestDTO extends UserRequestDTO {
  roleType: RoleType;
}