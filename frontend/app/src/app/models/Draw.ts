export type DrawStatus = 'OPEN' | 'CLOSED';

export interface Draw {
  drawId?: number;

  drawTime?: string;
  createdAt?: string;

  status?: DrawStatus;
}