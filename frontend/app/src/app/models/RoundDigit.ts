import { Draw } from './Draw';
import { DigitPosition } from './Bet';

export interface RoundDigit {
  roundDigitId?: number;

  draw?: Draw;

  digit: number;
  position: DigitPosition;
}