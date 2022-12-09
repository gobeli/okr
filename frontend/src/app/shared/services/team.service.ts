import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

export interface Team {
  id: number;
  name: string;
}

export interface OkrQuarter {
  quarter: string;
}

@Injectable({
  providedIn: 'root',
})
export class TeamService {
  constructor(private httpClient: HttpClient) {}

  public getTeams(): Observable<Team[]> {
    return this.httpClient
      .get<Team[]>('api/v1/teams')
      .pipe(tap((data) => console.log(data)));
  }

  public createTeam(name: string): Observable<Team> {
    const team = {
      name: name,
    } as Team;
    return this.httpClient.post<Team>('api/v1/teams', team);
  }

  public updateTeam(team: Team): Observable<Team> {
    return this.httpClient.put<Team>('api/v1/teams/' + team.id, team);
  }

  public getQuarter(date = new Date()) {
    let quarterList: OkrQuarter[] = [];

    let currentQuarter: number = Math.floor(date.getMonth() / 3 + 1);
    let currentYear: number = date.getFullYear();
    const currentCycle: OkrQuarter = {
      quarter:
        currentYear.toString().slice(-2) + '-' + currentQuarter.toString(),
    };
    quarterList!.push(currentCycle);

    let pastQuarter: number;
    let year: number = currentYear;
    for (let i = 0; i < 4; i++) {
      if (currentQuarter == 1) {
        (pastQuarter = 4), (year -= 1);
      } else {
        pastQuarter = currentQuarter - 1;
      }
      currentQuarter = pastQuarter;
      const pastCycle: OkrQuarter = {
        quarter: year.toString().slice(-2) + '-' + pastQuarter.toString(),
      };
      quarterList.push(pastCycle);
    }

    if (currentQuarter == 4) {
      (currentYear += 1), (currentQuarter = 1);
    } else {
      currentQuarter += 1;
    }
    const futureCycle: OkrQuarter = {
      quarter:
        currentYear.toString().slice(-2) + '-' + currentQuarter.toString(),
    };
    quarterList.push(futureCycle);

    return quarterList!;
  }
}
