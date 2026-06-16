export type RoleType = 'REGULAR' | 'ADMIN';

export interface User {
  userId: number;
  cpf: string;
  username: string;
  password?: string;
  email: string;
  role: RoleType;
  balance: number;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  cpf: string;
  password: string;
}

export interface UserUpdateRequest {
  username: string;
  email: string;
  password: string;
  cpf: string;
}
