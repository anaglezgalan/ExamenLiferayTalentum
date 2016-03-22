package com.examen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class TablaBlogUsuarios
 */

public class TablaBlogUsuarios extends MVCPortlet {

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {
			
			//Guardo los usuarios de Liferay en una lista
			List<User> usuarios = UserLocalServiceUtil.getUsers(0, UserLocalServiceUtil.getUsersCount());

			//Genero una nueva lista de usuarios donde cada usuario va a tener solo las propiedades 
			//que quiero mostrar en la tabla (de momento vacía)
			List<Usuario> usuariotabla = new ArrayList<Usuario>();

			
			//Recorro los usuarios de Liferay, y voy añadiendo a la lista que estaba vacía los datos
			//que luego quiero mostrar en la tabla
			
			for (int i = 1; i < UserLocalServiceUtil.getUsersCount(); i++) {
				usuariotabla.add(new Usuario(usuarios.get(i).getFirstName(), usuarios.get(i).getLastName(),
						usuarios.get(i).getScreenName(), usuarios.get(i).getEmailAddress()));
			}

			//Guardo la lista nueva de usuarios que he creado en un JSON
			String usuariosJSON = JSONFactoryUtil.serialize(usuariotabla);
			
			//Lo envío a las vistas
			resourceResponse.getWriter().print(usuariosJSON);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
