import { DigitPosition } from './bet.model';

export type DrawStatus = 'OPEN' | 'CLOSED';

export interface Draw {
  drawId: number;
  drawTime: string;
  status: DrawStatus;
  createdAt: string;
}

export interface RoundDigit {
  roundDigitId: number;
  digit: number;
  position: DigitPosition;
}
