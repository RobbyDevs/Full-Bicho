import { Component } from '@angular/core';
import { Menu } from '../menu/menu'
import { RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-bet',
  imports: [Menu, RouterOutlet],
  templateUrl: './bet.html',
  styleUrl: './bet.scss',
})
export class Bet {}
