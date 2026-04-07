export type RoleType = 'REGULAR' | 'ADMIN';

export interface User {
  userId?: number;
  cpf: string;

  username?: string;
  password?: string;
  email?: string;

  role?: RoleType;
  balance?: number;
}