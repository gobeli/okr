import { isInValid } from '../../src/app/shared/common';

export interface ScoringValue {
  failPercent: number;
  commitPercent: number;
  targetPercent: number;
}

export function validateScoringWidthsAndColor(isOverview: boolean, percentage: number) {
  let rgbCode = colorFromPercentage(percentage);
  let scoringValue = scoringValueFromPercentage(percentage);

  if (percentage >= 100) {
    cy.getZone('stretch', isOverview).should('have.attr', 'src').should('include', 'star-filled-icon.svg');
  }

  validateScoringWidth('fail', scoringValue.failPercent, isOverview);
  validateScoringWidth('commit', scoringValue.commitPercent, isOverview);
  validateScoringWidth('target', scoringValue.targetPercent, isOverview);

  validateScoringColor('fail', rgbCode, isOverview);
  validateScoringColor('commit', rgbCode, isOverview);
  validateScoringColor('target', rgbCode, isOverview);
}

export function validateScoringWidth(zone: string, percent: number, isOverview: boolean) {
  cy.getZone(zone, isOverview)
    .parent()
    .invoke('width')
    .then((parentWidth) => {
      expect(parentWidth).not.to.be.undefined;
      cy.getZone(zone, isOverview)
        .invoke('width')
        .should('be.within', parentWidth! * (percent / 100) - 3, parentWidth! * (percent / 100) + 3);
    });
}

export function validateScoringColor(zone: string, rgbCode: string, isOverview: boolean) {
  cy.getZone(zone, isOverview).invoke('css', 'background-color').should('equal', rgbCode);
  if (rgbCode == 'rgba(0, 0, 0, 0)') {
    cy.getZone(zone, isOverview).invoke('css', 'background-image').should('contain', 'scoring-stars.svg');
  }
}

export function getPercentageMetric(baseline: number, stretchGoal: number, value: number) {
  if (isInValid(baseline, stretchGoal, value)) {
    return -1;
  }
  return (Math.abs(value - baseline) / Math.abs(stretchGoal - baseline)) * 100;
}

export function getPercentageOrdinal(zone: string) {
  if (zone == 'stretch') return 101;
  if (zone == 'target') return 99;
  if (zone == 'commit') return 70;
  if (zone == 'fail') return 30;
  return 0;
}

export function colorFromPercentage(percentage: number) {
  if (percentage >= 100) return 'rgba(0, 0, 0, 0)';
  if (percentage > 70) return 'rgb(30, 138, 41)';
  if (percentage > 30) return 'rgb(255, 214, 0)';
  return 'rgb(186, 56, 56)';
}

export function scoringValueFromPercentage(percentage: number): ScoringValue {
  if (percentage >= 100) {
    return { failPercent: 100, commitPercent: 100, targetPercent: 101 };
  } else if (percentage > 70) {
    let targetPercent = (percentage - 70) * (100 / 30);
    return { failPercent: 100, commitPercent: 100, targetPercent: targetPercent };
  } else if (percentage > 30) {
    let commitPercent = (percentage - 30) * (100 / 40);
    return { failPercent: 100, commitPercent: commitPercent, targetPercent: -1 };
  }
  let failPercent = percentage * (100 / 30);
  return { failPercent: failPercent, commitPercent: -1, targetPercent: -1 };
}
