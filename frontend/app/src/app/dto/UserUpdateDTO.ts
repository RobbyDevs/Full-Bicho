export interface UserUpdateDTO {
  username?: string;
  email?: string;

  password: string;
  cpf: string;

  balance?: number;
}