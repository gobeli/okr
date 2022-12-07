import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Objective {
  id: number;
  title: string;
  ownerId: number;
  ownerFirstname: string;
  ownerLastname: string;
  quarterId: number;
  quarterNumber: number;
  quarterYear: number;
  description: string;
  progress: number;
  created: string;
}

@Injectable({
  providedIn: 'root',
})
export class ObjectiveService {
  constructor(private httpClient: HttpClient) {}

  public getObjectivesOfTeam(teamId: number): Observable<Objective[]> {
    return this.httpClient.get<Objective[]>(
      'api/v1/teams/' + teamId + '/objectives'
    );
  }
}
