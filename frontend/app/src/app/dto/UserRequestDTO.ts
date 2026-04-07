export interface UserRequestDTO {
  username: string;
  password: string;
  email: string;
  cpf: string;

  balance?: number;
}