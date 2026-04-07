export type DrawStatus = 'OPEN' | 'CLOSED';

export interface DrawRequestDTO {
  drawTime?: string;   // LocalDateTime -> string ISO
  status?: DrawStatus;
  createdAt?: string;
}