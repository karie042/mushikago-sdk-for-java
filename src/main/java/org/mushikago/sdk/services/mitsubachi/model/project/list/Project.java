package org.mushikago.sdk.services.mitsubachi.model.project.list;

/**
 * プロジェクト一覧で取得できるProjectの表現。<br>
 * @author miningbrownie
 *
 */
public class Project {
	
	/**
	 * レスポンス(project_name)。<br>
	 */
	private final String name;
	
	/**
	 * 指定されたプロジェクト名を使用して、Projectを構築します。<br>
	 */
	public Project(String name) {
		
		this.name = name;
	}
	
	/**
	 * プロジェクト名の取得。<br>
	 * @return プロジェクト名
	 */
	public String getName() { return name; }
}
