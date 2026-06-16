export type BetType = 'GROUP' | 'TENS' | 'HUNDREDS' | 'THOUSANDS';
export type BetStatus = 'PENDING' | 'WIN' | 'LOSE';
export type DigitPosition = 'HEAD' | 'SECOND' | 'THIRD' | 'FOURTH' | 'FIFTH';

export interface BetRequest {
  userId: number;
  drawId: number;
  type: BetType;
  chosenNumber: number;
  amount: number;
}

export interface Bet {
  betId: number;
  userId?: number;
  drawId?: number;
  type: BetType;
  chosenNumber: number;
  amount: number;
  status: BetStatus;
  resultNumber?: number | null;
  digitPosition?: DigitPosition | null;
  payout: number;
  createdAt?: string | null;
}

export interface BetStats {
  total: number;
  wins: number;
  losses: number;
  pending: number;
  winRate: number;
  totalStaked: number;
  totalWon: number;
  totalLost: number;
  profit: number;
}
