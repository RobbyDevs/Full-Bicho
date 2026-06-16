import { Injectable } from '@angular/core';
import { Animal } from '../models/animal.model';
import { BetType } from '../models/bet.model';

@Injectable({ providedIn: 'root' })
export class AnimalService {
  readonly animals: Animal[] = [
    { group: 1, name: 'Avestruz', tens: ['01', '02', '03', '04'], icon: '🦩' },
    { group: 2, name: 'Águia', tens: ['05', '06', '07', '08'], icon: '🦅' },
    { group: 3, name: 'Burro', tens: ['09', '10', '11', '12'], icon: '🐴' },
    { group: 4, name: 'Borboleta', tens: ['13', '14', '15', '16'], icon: '🦋' },
    { group: 5, name: 'Cachorro', tens: ['17', '18', '19', '20'], icon: '🐶' },
    { group: 6, name: 'Cabra', tens: ['21', '22', '23', '24'], icon: '🐐' },
    { group: 7, name: 'Carneiro', tens: ['25', '26', '27', '28'], icon: '🐏' },
    { group: 8, name: 'Camelo', tens: ['29', '30', '31', '32'], icon: '🐫' },
    { group: 9, name: 'Cobra', tens: ['33', '34', '35', '36'], icon: '🐍' },
    { group: 10, name: 'Coelho', tens: ['37', '38', '39', '40'], icon: '🐰' },
    { group: 11, name: 'Cavalo', tens: ['41', '42', '43', '44'], icon: '🐎' },
    { group: 12, name: 'Elefante', tens: ['45', '46', '47', '48'], icon: '🐘' },
    { group: 13, name: 'Galo', tens: ['49', '50', '51', '52'], icon: '🐓' },
    { group: 14, name: 'Gato', tens: ['53', '54', '55', '56'], icon: '🐈' },
    { group: 15, name: 'Jacaré', tens: ['57', '58', '59', '60'], icon: '🐊' },
    { group: 16, name: 'Leão', tens: ['61', '62', '63', '64'], icon: '🦁' },
    { group: 17, name: 'Macaco', tens: ['65', '66', '67', '68'], icon: '🐒' },
    { group: 18, name: 'Porco', tens: ['69', '70', '71', '72'], icon: '🐷' },
    { group: 19, name: 'Pavão', tens: ['73', '74', '75', '76'], icon: '🦚' },
    { group: 20, name: 'Peru', tens: ['77', '78', '79', '80'], icon: '🦃' },
    { group: 21, name: 'Touro', tens: ['81', '82', '83', '84'], icon: '🐂' },
    { group: 22, name: 'Tigre', tens: ['85', '86', '87', '88'], icon: '🐅' },
    { group: 23, name: 'Urso', tens: ['89', '90', '91', '92'], icon: '🐻' },
    { group: 24, name: 'Veado', tens: ['93', '94', '95', '96'], icon: '🦌' },
    { group: 25, name: 'Vaca', tens: ['97', '98', '99', '00'], icon: '🐄' }
  ];

  getByGroup(group: number): Animal | undefined {
    return this.animals.find(animal => animal.group === Number(group));
  }

  getGroupFromNumber(number: number): number {
    const tens = Math.abs(Number(number)) % 100;
    if (tens === 0) {
      return 25;
    }
    return Math.floor((tens - 1) / 4) + 1;
  }

  getAnimalFromNumber(number: number): Animal | undefined {
    return this.getByGroup(this.getGroupFromNumber(number));
  }

  padNumber(number: number, type: BetType): string {
    const sizes: Record<BetType, number> = {
      GROUP: 2,
      TENS: 2,
      HUNDREDS: 3,
      THOUSANDS: 4
    };

    return String(number ?? 0).padStart(sizes[type], '0');
  }
}
