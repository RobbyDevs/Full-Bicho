
export type BetType = 'GROUP' | 'TENS' | 'HUNDREDS' | 'THOUSANDS';

export interface BetRequestDTO {
  userId: number;
  drawId: number;

  type: BetType;
  chosenNumber: number;
  amount: number;
}