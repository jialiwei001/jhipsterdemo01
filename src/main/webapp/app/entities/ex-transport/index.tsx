import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ExTransport from './ex-transport';
import ExTransportDetail from './ex-transport-detail';
import ExTransportUpdate from './ex-transport-update';
import ExTransportDeleteDialog from './ex-transport-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExTransportUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExTransportUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExTransportDetail} />
      <ErrorBoundaryRoute path={match.url} component={ExTransport} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ExTransportDeleteDialog} />
  </>
);

export default Routes;
