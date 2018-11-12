import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IExTransport, defaultValue } from 'app/shared/model/ex-transport.model';

export const ACTION_TYPES = {
  FETCH_EXTRANSPORT_LIST: 'exTransport/FETCH_EXTRANSPORT_LIST',
  FETCH_EXTRANSPORT: 'exTransport/FETCH_EXTRANSPORT',
  CREATE_EXTRANSPORT: 'exTransport/CREATE_EXTRANSPORT',
  UPDATE_EXTRANSPORT: 'exTransport/UPDATE_EXTRANSPORT',
  DELETE_EXTRANSPORT: 'exTransport/DELETE_EXTRANSPORT',
  RESET: 'exTransport/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IExTransport>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ExTransportState = Readonly<typeof initialState>;

// Reducer

export default (state: ExTransportState = initialState, action): ExTransportState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EXTRANSPORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EXTRANSPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EXTRANSPORT):
    case REQUEST(ACTION_TYPES.UPDATE_EXTRANSPORT):
    case REQUEST(ACTION_TYPES.DELETE_EXTRANSPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EXTRANSPORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EXTRANSPORT):
    case FAILURE(ACTION_TYPES.CREATE_EXTRANSPORT):
    case FAILURE(ACTION_TYPES.UPDATE_EXTRANSPORT):
    case FAILURE(ACTION_TYPES.DELETE_EXTRANSPORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXTRANSPORT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXTRANSPORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EXTRANSPORT):
    case SUCCESS(ACTION_TYPES.UPDATE_EXTRANSPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EXTRANSPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/ex-transports';

// Actions

export const getEntities: ICrudGetAllAction<IExTransport> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EXTRANSPORT_LIST,
    payload: axios.get<IExTransport>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IExTransport> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EXTRANSPORT,
    payload: axios.get<IExTransport>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IExTransport> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EXTRANSPORT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IExTransport> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EXTRANSPORT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IExTransport> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EXTRANSPORT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
