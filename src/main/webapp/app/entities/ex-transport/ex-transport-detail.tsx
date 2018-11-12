import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ex-transport.reducer';
import { IExTransport } from 'app/shared/model/ex-transport.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExTransportDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ExTransportDetail extends React.Component<IExTransportDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { exTransportEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterdemo01App.exTransport.detail.title">ExTransport</Translate> [<b>{exTransportEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="ex_extransport">
                <Translate contentKey="jhipsterdemo01App.exTransport.ex_extransport">Ex Extransport</Translate>
              </span>
            </dt>
            <dd>{exTransportEntity.ex_extransport}</dd>
            <dt>
              <span id="ex_extransporta">
                <Translate contentKey="jhipsterdemo01App.exTransport.ex_extransporta">Ex Extransporta</Translate>
              </span>
            </dt>
            <dd>{exTransportEntity.ex_extransporta}</dd>
          </dl>
          <Button tag={Link} to="/entity/ex-transport" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ex-transport/${exTransportEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ exTransport }: IRootState) => ({
  exTransportEntity: exTransport.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExTransportDetail);
