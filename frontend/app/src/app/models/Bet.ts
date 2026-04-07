import { User } from './User';
import { Draw } from './Draw';

export type BetType = 'GROUP' | 'TENS' | 'HUNDREDS' | 'THOUSANDS';

export type BetStatus = 'PENDING' | 'WIN' | 'LOSE';

export type DigitPosition = 'HEAD' | 'SECOND' | 'THIRD' | 'FOURTH' | 'FIFTH';

export interface Bet {
  betId?: number;

  userId: number;
  drawId: number;

  type: BetType;
  chosenNumber: number;
  amount: number;

  status?: BetStatus;
  resultNumber?: number;
  digitPosition?: DigitPosition;

  payout?: number;
}