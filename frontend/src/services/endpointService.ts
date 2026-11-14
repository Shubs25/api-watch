import type { NewEndpoint } from '../components/AddEndpointModal'
import type { Endpoint } from '../type/endpoint'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function createEndpoint(endpoint: NewEndpoint) {
    const response = await fetch(`${API_BASE_URL}/api/endpoints`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            ...endpoint,
            active: true,
        }),
    })

    if (!response.ok) {
        const errorBody = await response.text()
        throw new Error(
            errorBody || `Failed to create endpoint (${response.status})`
        )
    }

    const responseBody = await response.text()

    if (!responseBody) {
        return null
    }

    try {
        return JSON.parse(responseBody)
    } catch {
        return responseBody
    }
}


export async function getEndpoints(): Promise<Endpoint[]> {
  const response = await fetch(`${API_BASE_URL}/api/endpoints`)

  if (!response.ok) {
    throw new Error(`Failed to fetch endpoints (${response.status})`)
  }

  return response.json()
}


export async function triggerHealthCheck(endpointId: string): Promise<void> {
  const response = await fetch(
    `${API_BASE_URL}/api/endpoints/${endpointId}/check`,
    {
      method: 'POST',
    }
  )

  if (!response.ok) {
    const errorBody = await response.text()
    throw new Error(
      errorBody || `Failed to trigger health check (${response.status})`
    )
  }
}
