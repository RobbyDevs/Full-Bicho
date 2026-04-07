import { UserUpdateDTO } from './UserUpdateDTO';
import { RoleType } from './AdminRequestDTO';

export interface AdminUpdateDTO extends UserUpdateDTO {
  role: RoleType;
  balance?: number;
}