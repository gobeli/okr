Cypress.Commands.add('loginAsUser', (user: any) => {
  loginWithCredentials(user.username, user.password);
  overviewIsLoaded();
});

Cypress.Commands.add('getByTestId', { prevSubject: 'optional' }, (subject: any, testId: string) => {
  if (subject) {
    return cy.wrap(subject).find(`[data-testId=${testId}]`);
  }
  return cy.get(`[data-testId=${testId}]`);
});

Cypress.Commands.add(
  'fillOutObjective',
  (
    objectiveTitle: string,
    button: string,
    quarter?: string,
    description?: string,
    createKeyResults: boolean = false,
  ) => {
    cy.getByTestId('title').first().clear().type(objectiveTitle);
    cy.getByTestId('description')
      .first()
      .clear()
      .type(description || 'This is the description of the new Objective');
    if (quarter) {
      cy.get('select#quarter').select(quarter);
    }
    if (createKeyResults) {
      cy.getByTestId('keyResult-checkbox').find("[type='checkbox']").check();
    }
    cy.getByTestId(button).click();
  },
);

Cypress.Commands.add(
  'fillOutCheckInMetric',
  (currentValue: number, confidence: number, changeInfo: string | null, initiatives: string | null) => {
    cy.getByTestId('check-in-metric-value').clear().type(currentValue.toString());
    changeConfidence(confidence);
    cy.getByTestId('check-in-next').click();
    if (changeInfo) {
      cy.getByTestId('changeInfo').clear().type(changeInfo!);
    }
    if (initiatives) {
      cy.getByTestId('initiatives').clear().type(initiatives!);
    }
    cy.getByTestId('submit-check-in').click();
  },
);

Cypress.Commands.add(
  'fillOutCheckInOrdinal',
  (currentZoneIndex: number, confidence: number, changeInfo: string | null, initiatives: string | null) => {
    switch (currentZoneIndex) {
      case 0:
        cy.getByTestId('failZone').click();
        break;
      case 1:
        cy.getByTestId('commitZone').click();
        break;
      case 2:
        cy.getByTestId('targetZone').click();
        break;
      case 3:
        cy.getByTestId('stretchZone').click();
        break;
    }
    changeConfidence(confidence);
    cy.getByTestId('check-in-next').click();
    if (changeInfo) {
      cy.getByTestId('changeInfo').clear().type(changeInfo!);
    }
    if (initiatives) {
      cy.getByTestId('initiatives').clear().type(initiatives!);
    }
    cy.getByTestId('submit-check-in').click();
  },
);

Cypress.Commands.add('tabForward', () => {
  cy.realPress('Tab');
});

Cypress.Commands.add('tabBackward', () => {
  cy.realPress(['Shift', 'Tab']);
});

Cypress.Commands.add('tabForwardUntil', (selector: string, limit?: number) => {
  doUntil(selector, cy.tabForward, limit);
});

Cypress.Commands.add('tabBackwardUntil', (selector: string, limit?: number) => {
  doUntil(selector, cy.tabBackward, limit);
});

Cypress.Commands.add(
  'fillOutKeyResult',
  (
    title: string,
    unit: string | null,
    baseline: string | null,
    stretchGoal: string | null,
    commitZone: string | null,
    targetZone: string | null,
    stretchZone: string | null,
    owner: string | null,
    description: string,
  ) => {
    cy.getByTestId('titleInput').clear().type(title);
    if (commitZone == null) {
      cy.getByTestId('unit').select(unit!);
      cy.getByTestId('baseline').clear().type(baseline!);
      cy.getByTestId('stretchGoal').clear().type(stretchGoal!);
    } else {
      cy.getByTestId('commitZone').clear().type(commitZone!);
      cy.getByTestId('targetZone').clear().type(targetZone!);
      cy.getByTestId('stretchZone').clear().type(stretchZone!);
    }
    if (owner != null) {
      cy.getByTestId('ownerInput').clear().type(owner).type('{downarrow}').type('{enter}');
    }
    cy.getByTestId('descriptionInput').clear().type(description);
  },
);

function changeConfidence(confidence: number) {
  if (confidence > 5) {
    cy.getByTestId('confidence-slider').realMouseDown();
    for (let i = 0; i < confidence - 5; i++) {
      cy.getByTestId('confidence-slider').type('{rightarrow');
    }
    cy.getByTestId('confidence-slider').realMouseUp();
  } else {
    cy.getByTestId('confidence-slider').realMouseDown();
    for (let i = 0; i < 5 - confidence; i++) {
      cy.getByTestId('confidence-slider').type('{leftarrow');
    }
    cy.getByTestId('confidence-slider').realMouseUp();
  }
}

function doUntil(selector: string, tab: () => void, limit: number = 20) {
  for (let i = 0; i < limit; i++) {
    cy.focused().then((element) => {
      if (element.get(0).matches(selector)) {
        return;
      } else {
        tab();
      }
    });
  }
}

function loginWithCredentials(username: string, password: string) {
  cy.visit('/');
  cy.origin(Cypress.env('login_url'), { args: { username, password } }, ({ username, password }) => {
    cy.get('input[name="username"]').type(username);
    cy.get('input[name="password"]').type(password);
    cy.get('input[type="submit"]').click();
  });
  cy.url().then((url) => {
    const currentUrl = new URL(url);
    const baseURL = new URL(Cypress.config().baseUrl!);
    expect(currentUrl.pathname).equal(baseURL.pathname);
  });
}

const overviewIsLoaded = () =>
  cy.get('mat-chip').should('have.length.at.least', 2) && cy.get('.team-title').should('have.length.at.least', 1);

// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
