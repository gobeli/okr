import { State } from '../enums/State';
import { TeamMin } from './TeamMin';
import { Quarter } from './Quarter';

export interface Objective {
  id: number;
  title: string;
  description: string;
  state: State;
  teamId: number;
  quarterId: number;
  createdOn: Date;
  modifiedOn: Date;
}
